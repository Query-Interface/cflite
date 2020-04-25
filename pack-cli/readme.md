usage:
docker run --interactive --tty --rm -v //var/run/docker.sock:/var/run/docker.sock -v //c/Projects/sample-source-code:/code pack-cli build pack-cli-app-test --builder cnbs/sample-builder:bionic


current issue: sample image built !
 - use bind mount docker.sock to use docker on the host (to avoid docker in docker). See https://github.com/docker/for-win/issues/1829
 - the volume is used to store the code to compile, so update it to change the project compiled

Next step: create a service
    - retrieves the code (gzip or stored somewhere?)
    - launch pack to compile it
    - publish the image in a private registry
