FROM alpine:latest
ENV VERSION="v0.10.0"

RUN apk update && \
	apk upgrade && \
	apk add wget && \
	apk add tar

RUN	apk add docker

RUN	apk add openrc && \
	rc-update add docker boot

RUN	wget https://github.com/buildpacks/pack/releases/download/${VERSION}/pack-${VERSION}-linux.tgz  && \
	tar xvf pack-v0.10.0-linux.tgz && \
	rm pack-v0.10.0-linux.tgz && \
	mv ./pack /usr/bin  && \
	/usr/bin/pack --help

VOLUME /code

WORKDIR /code
ENTRYPOINT ["/usr/bin/pack"]
