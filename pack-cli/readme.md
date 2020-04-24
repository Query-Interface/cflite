usage:
docker run --interactive --tty --rm -v //c/Projects/sample-source-code:/code pack-cli build pack-cli-app-test --builder cnbs/sample-builder:bionic

current issue: (find how to wait until docker daemon is started)
ERROR: failed to fetch builder image 'index.docker.io/cnbs/sample-builder:bionic': Cannot connect to the Docker daemon at unix:///var/run/docker.sock. Is the docker daemon running?

