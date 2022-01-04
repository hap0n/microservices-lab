docker build -t hap0n/service-fields:latest service-fields/
docker push hap0n/service-fields

docker build -t hap0n/service-orders:latest service-orders/
docker push hap0n/service-orders

docker build -t hap0n/service-users:latest service-users/
docker push hap0n/service-users

minikube stop && minikube delete
minikube start

kubectl apply -f service-fields/workloads.yaml
kubectl apply -f service-orders/workloads.yaml
kubectl apply -f service-users/workloads.yaml

echo "$(minikube ip)"
