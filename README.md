# deploy-util
Test task for artifacts deployment to servlet container

<h1>Prerequisites</h1>
In order to run this software you need to have:
<ul>
<li>JDK 1.8</li>
<li>Tomcat 8 installed with configured user for manager application</li>
</ul>

<h1>Application build</h1>
To build the project from sources please run:
<pre>
./gradlew build -x test
</pre>

<h1>Application run</h1>
This is an example command line to run deployment tool:
<pre>
./tool.sh \
    --command=deploy \
    --config=/home/user/config.yml \
    --username=tomcat \
    --artifact=/home/user/sample.war \
    --serverUrl=http://localhost:8080
</pre>

<pre>
              +--------------+             +--------------+
              |              |             |              |
              |  tool.sh     |             |  spring      |
USER input    |  wrapper     |             |  boot        |
              |  for         |             |  application |
     +-------->  parameters  +------------->              |
              |  mapping     |             |              |
              |              |             |              |
              |              |             |              |
              |              |             |              |
              |              |             |              |
              |              |             |              |
              |              |             |              |
              +--------------+             +--------------+

</pre>

This is the sample output of a program. Application sample.war is deployed, then stopped, then started and undeployed.
The last line shows that after undeploy no application sample is left at Tomcat.
<pre>
Successfully deployed application: sample
Successfully stopped application: sample
Status=/sample:stopped:0:sample of application: sample
Successfully started application: sample
Status=/sample:running:0:sample of application: sample
Successfully undeployed application: sample
Status=APPLICATION_NOT_AVAILABLE of application: sample
</pre>

Internally <b>tool.sh</b> executes the following command:
<pre>
java -jar ./deploy-util-0.0.1-SNAPSHOT.jar \
    --username tomcat \
    --password s3cret \
    --spring.config.location /home/user/config.yml \
    --artifact /home/user/sample.war
</pre>

