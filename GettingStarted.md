# Introduction #
Some usefull conf/mo, mostly kept as a reminder for next time.

# Setups #
  * Git
  * Java JDK
  * Maven
  * Eclipse EE

# Git config #

```
git config --global user.name "John Doe"
git config --global user.email johndoe@example.com
git config --global http.proxy http://proxyuser:proxypwd@proxy.server.com:8080
git config --system http.sslcainfo /bin/curl-ca-bundle.crt
```

# Maven config #
in conf/settings.xml
```
<localRepository>path/to/repo</localRepository>
<proxies>
	<proxy>
		<active>true</active>
		<protocol>http</protocol>
		<host>proxy.server.com</host>
		<port>8080</port>
		<username>proxyuser</username>
		<password>proxypwd</password>
		<nonProxyHosts>www.google.com|*.somewhere.com</nonProxyHosts>
	</proxy>
</proxies>
<profile
	<profile>
		<id>aProfile</id>
		<activation>
			<activeByDefault>true</activeByDefault>
		</activation>

		<repositories>
			<repository>
				<id>release.repo</id>
				<url>http://release.url.repo</url>
				<releases>
					<enabled>true</enabled>
				</releases>
				<snapshots>
					<enabled>false</enabled>
				</snapshots>
			</repository>

			<repository>
				<id>snapshot.repo</id>
				<url>http://snapshot.url.repo</url>
				<releases>
					<enabled>true</enabled>
				</releases>
				<snapshots>
					<enabled>false</enabled>
				</snapshots>
			</repository>
		</repositories>

		<pluginRepositories>
			<pluginRepository>
				<id>release.pluginrepo</id>
				<url>http://release.url.pluginrepo</url>
				<releases>
					<enabled>true</enabled>
				</releases>
				<snapshots>
					<enabled>false</enabled>
				</snapshots>
			</pluginRepository>

			<pluginRepository>
				<id>snapshot.pluginrepo</id>
				<url>http://snapshot.url.pluginrepo</url>
				<releases>
					<enabled>true</enabled>
				</releases>
				<snapshots>
					<enabled>false</enabled>
				</snapshots>
			</pluginRepository>
		</pluginRepositories>
	</profile>
</profiles>
```

# Win config #

| GIT\_HOME | path\to\git |
|:----------|:------------|
| GIT\_BIN  | %GIT\_HOME%\bin  |
| MAVEN\_HOME | path\to\maven |
| MAVEN\_OPTS | some ops    |
| MAVEN\_BIN| %MAVEN\_HOME%\bin |
| JAVA\_HOME | path\to\jdk |
| JAVA\_OPTS | some ops    |
| JAVA\_BIN | %JAVA\_HOME%\bin |
| PATH      | %PATH%;%GIT\_BIN%;%JAVA\_BIN%;%MAVEN\_BIN% |

# Init Workspace #

```
mkdir workspace
cd workspace
git clone https://proxyuser:projectpwd@code.google.com/p/incrementer/
mvn eclipse:clean eclipse:eclipse
```

# Running the project with jetty #
```
mvn jetty:run-war
```
or
```
mvn jetty:run-exploded
```
or
```
cd incrementer-wicket
mvn jetty:run
```

# Eclipse #
## Eclipse Pluggin ##

  * EGit
  * MoreUnit
  * RunJettyRun

## Eclipse Conf ##

  * Add downloaded Maven as default
  * Add downloaded JDK as default
  * Import existing project from workspace, Check the nested dir checkbox, get the projects (incrementer-core, incrementer-app, incrementer-wicket)

## Eclipse Launch Configs ##

### Maven ###
For each maven conf, make sure the default JDK and maven install are the good ones.
| incrementer reset | Base Directory | ${workspace\_loc}/incrementer |
|:------------------|:---------------|:------------------------------|
| incrementer  reset | Goals          | eclipse:clean eclipse:eclipse |
| incrementer build | Base Directory | ${workspace\_loc}/incrementer |
| incrementer build | Goals          | clean install                 |
| incrementer test  | Base Directory | ${workspace\_loc}/incrementer |
| incrementer test  | Goals          | test                          |
| incrementer update version | Base Directory | ${workspace\_loc}/incrementer |
| incrementer update version | Goals          | versions:set -DnewVersion=${version} |
| incrementer update version | parameters     |version : ${string\_prompt:the new version} |

### Jetty ###

Project (increment-wicket) > Run As > Run Jetty
_(will do the trick \o/)_
# "Let's test... for science..." #
http://localhost:8080/incrementer/