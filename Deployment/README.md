# Deployment scripts for Kubernetes

We're going to be using Github Actions and connecting to Azure, so before we do that we will need to 
add a Secret in our Github project that allows us to authenticate against azure.

Below is a script that will generate that credential for us if we're already logged in to our azure
account via the command prompt.

```bash
az ad sp create-for-rbac -n "spring-boot-api" --role Contributor --scopes /subscriptions/[your-subscription-id] --sdk-auth
```


