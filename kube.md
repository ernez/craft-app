https://kubernetes.io/docs/reference/kubectl/cheatsheet/

kubectl config current-context

kubectl apply -k ./deployments

kubectl create secret generic postgres-user-pass --from-literal=password=123 --from-literal=username=postgres

echo -n 'postgres' | base64
echo -n 'MTIzCg==' | base64 --decode

//Test defined PVs:
kubectl get persistentvolumes
kubectl get persistentvolumeclaims

kubectl get services

kubectl get pods
kubectl exec -it craft-app-postgres-8ff8684d7-w2ptn -- /bin/bash

//switch use to postgres:
su - postgres

psql -h localhost postgres

//rin psql help command:
type \h 

Testiranje nakon deployment-a:

U Idea Kube pluginu s emoze procitati external IP za NpodePort Service - craft-app-server, i to je localhost
NodePOrt se moze procitai iz service-a:

takod ase prostupa na kraju preko http://localhost:31276


