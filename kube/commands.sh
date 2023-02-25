docker run --rm -it --name=PhpDev -p 8080:80 php:8.1.16-apache
docker exec -it PhpDev /bin/bash

docker build -t php-kube-lookup:latest .
docker build -t php-kube-lookup:minikube .

docker run --rm -it --name=PhpDev -p 8080:80 php-kube-lookup:latest
#or for not latest
docker run --rm -it --name=PhpDev -p 8080:80 php-kube-lookup:minikube

#minikube image build -t php-kube-lookup:latest .
minikube image build -t php-kube-lookup:minikube .
#OR
eval $(minikube docker-env)
#then build 
minikube tunnel

minikube image load php-kube-lookup:minikube # it takes way more time than building in minikube
minikube image list

#kubectl run lookup-test --image-pull-policy=IfNotPresent --image=php-kube-lookup:latest --port=80
#SIC!! - image-pull-policy is required for :latest, therefore use other tag for local

kubectl run lookup-test --image=php-kube-lookup:minikube --port=80
kubectl exec lookup-test -ti -- /bin/bash

docker pull busybox:1.35
docker run --rm -it busybox:1.35

#https://kubernetes.io/docs/tasks/debug/debug-application/debug-running-pod/#ephemeral-container
kubectl debug -it lookup-test --image=busybox:1.35 --target=lookup-test
## processes are shared; i.e. ps -a shows apache 

# /proc/XX/root/ << to access other processes file system

kubectl apply -f lookup-test-pod.yaml # one instance manually created
kubectl apply -f lookup-test-pod-sibling.yaml # another one manually created
kubectl apply -f apply lookup-test-podservice.yaml # to create sibling service for 18080 to same pods; matching two above

curl http://localhost:18080/ | grep "MY_POD_NAME =>" # multiple times should show both nodes being served
kubectl delete pod/lookup-test # delete single node, so only sibling is served