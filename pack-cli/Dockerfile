FROM alpine:latest
RUN apk update && \
	apk upgrade && \
	apk add wget && \
	apk add tar && \
	wget https://github.com/buildpacks/pack/releases/download/v0.10.0/pack-v0.10.0-linux.tgz  && \
	tar xvf pack-v0.10.0-linux.tgz && \
	rm pack-v0.10.0-linux.tgz && \
	./pack --help
ENTRYPOINT ["./pack"] 
	
