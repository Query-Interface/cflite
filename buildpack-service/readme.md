Command to launch the container and connect to the same network as minio

docker run --tty --rm --name=buildpack-service --env MINIO_URL=http://minio1:9000 -v //var/run/docker.sock:/var/run/docker.sock -p 127.0.0.1:8090:8080/tcp buildpack-service