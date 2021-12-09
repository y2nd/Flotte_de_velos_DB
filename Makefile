CLASSPATH=/home/oracle/app/oracle/product/11.2.0/dbhome_1/jdbc/lib/ojdbc5.jar:.:${BUILD_DIR}:CLASSPATH

APP_DIR=./Application
BUILD_DIR=./build
HTML_DIR=./html

all : build

build: clean main

run:
	java -cp $(CLASSPATH) Main

main: $(APP_DIR)/*.java
	mkdir $(BUILD_DIR)
	javac -d $(BUILD_DIR) -cp $(CLASSPATH) $(APP_DIR)/*.java

clean:
	rm -rf $(HTML_DIR) $(BUILD_DIR)