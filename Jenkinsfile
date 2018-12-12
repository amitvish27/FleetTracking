node{
	def DOCKER_REPO="amitvish27/trucker-api"
	def DOCKER_SERVICE_ID="trucker-service"
	def DOCKER_IMAGE_VERSION=""

	stage("clean workspace") {
		deleteDir()
	}

	stage("git checkout") {
		checkout scm
		def GIT_COMMIT=sh(returnStdout: true, script: "git rev-parse HEAD").trim().take(7)
		DOCKER_IMAGE_VERSION="${BUILD_NUMBER}-${GIT_COMMIT}"
	}

	stage("mvn build") {
		sh "mvn clean install"
	}

	stage("docker build") {
		sh "docker build -t ${DOCKER_REPO}:${DOCKER_IMAGE_VERSION} ."
	}

	stage("docker push") { 
		withDockerRegistry(credentialsId: 'dockerhub') {
			sh "docker push ${DOCKER_REPO}:${DOCKER_IMAGE_VERSION}"
		}
	}

	stage("docker service") {
		//create the service if it doesn't exists otherwise just updated the image
		try {
			sh """
				if [\$(docker service ls --filter name=${DOCKER_SERVICE_ID} --quiet | wc -l) -eq 0]; then
					docker service create \
					--replicas 1 \
					--name ${DOCKER_SERVICE_ID} \
					--env spring.profiles.active=prod \
					${DOCKER_REPO}:${DOCKER_IMAGE_VERSION}
				else 
					docker service update \
						--image ${DOCKER_REPO}:${DOCKER_IMAGE_VERSION} \
						${DOCKER_SERVICE_ID}
				fi
			"""
		}
		catch(e) {
			sh "docker service update --rollback ${DOCKER_SERVICE_ID}"
			error "Service udpate failed. Rolling back ${DOCKER_SERVICE_ID}"
		}
		finally {
			sh "docker container prune -f"
			sh "docker image prune -af"
		}
	}

	stage("cleanup") {
		sh "docker container prune -f"
		sh "docker rmi ${DOCKER_REPO}:${DOCKER_IMAGE_VERSION}"
	}
}