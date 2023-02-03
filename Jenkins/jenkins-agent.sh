# vv from docker tutorial
#docker run -i --rm --network devnet --name jenkins-agent01 --init jenkins/agent java -jar /usr/share/jenkins/agent.jar

# vv from jenkins itself
# + it must be registered with /home/jenkins/agent
docker run -i --rm --network devnet --name jenkins-agent01 --init jenkins/agent java -jar /usr/share/jenkins/agent.jar -jnlpUrl http://jenkins:8080/manage/computer/jenkins%2Dagent01/jenkins-agent.jnlp -secret XXXX965637f86148d643b494b194a59c929c1f4ca6b531d95f376b57b57bb54d -workDir /home/jenkins/agent