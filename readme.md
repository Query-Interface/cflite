# CFLite

## Goal
In this project, I want to study how a PaaS like Cloud Foundry can be implemented to be deployed in Kubernetes. It will allow to put in practice my learnings on k8s and give some answers to questions regarding k8s in regards to Cloud Foundry which I often use.

For a real project, have a look at The [Cloud Foundry for Kubernetes](https://www.cloudfoundry.org/blog/cloud-foundry-becomes-more-kubernetes-native-with-cf-for-k8s/) that have been announced recently.

## Some details
In the scope of this project, I plan to reuse the Cloud Foundry CLI to upload an application to the Kubernetes cluster, compile the source and create a Docker image. This image will be pushed into a private registry. Then the a deployment will be created to deploy this image and it will be exposed with a service.

![Cloud foundry architecture diagram](https://docs.cloudfoundry.org/concepts/images/cf_architecture_block.png "Cloud Foundry architecture")

List of components that will be developed:
- minimal UAA components to create a valid auth token
- some cloud controller features to respond to supported cf cli command
- blob store to store application code packages
- diego cells equivalent will be implement using buildpacks.io and k8s features
- services will be implemented to allow developers to provision and bind a service to an application. For example, a database service may be provided
- for observality an equivalent of the loggregator and metrics collector will be developed.

Cli commands to support:
- login: provide an authentication token
- organizations: list of organizations
- spaces: list of spaces
- apps: list of application
- app: detail on an application
- push: deploy an application
- start: start an application
- stop: stop an application
- logs: display logs of an application
- service: bind a service to an application

## Current development state and plans
- REST APIs for UAA and Cloud Controller to retrieve to respond to CF cli commands: api, login, organizations, spaces (some with mocked repsonses)
- kpack will be used to compile code with buidpacks.io
- minio will be used as blob storage
- a private docker registry will be deployed in k8s to store images created by the buildpacks
- ...