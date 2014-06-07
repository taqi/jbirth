#!/bin/sh

PROJECT_NAME=spy

function changeProjectSpecificString {
	echo $1
	sed -i "s/jbirth/$PROJECT_NAME/g" $1
}
changeProjectSpecificString pom.xml
changeProjectSpecificString init-script.cql
find ./src -type f -name '*.java' | while read file; do changeProjectSpecificString "$file"; done
find ./src/main/webapp/ -type f -name '*.xml' | while read file; do changeProjectSpecificString "$file"; done
find ./src/main/resources/ -type f -name '*.jsp' | while read file; do changeProjectSpecificString "$file"; done
mv ./src/main/java/com/keebraa/jbirth ./src/main/java/com/keebraa/$PROJECT_NAME
mv ./src/test/java/com/keebraa/jbirth ./src/test/java/com/keebraa/$PROJECT_NAME
