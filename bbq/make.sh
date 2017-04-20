TOP_DIR=$(pwd)

LIBPATH=lib/commons-fileupload-1.3.1.jar:lib/commons-io-2.4.jar:lib/mysql-connector-java-5.1.39-bin.jar:WEN-INF/classes

javac                                WEB-INF/classes/db/User.java  
javac                                WEB-INF/classes/db/ConnDB.java    
javac -cp $TOP_DIR/WEB-INF/classes:$LIBPATH  WEB-INF/classes/db/UseDB.java      
javac -cp $TOP_DIR/WEB-INF/classes:$LIBPATH  WEB-INF/classes/web/DateServlet.java  -Xlint:deprecation
javac -cp $TOP_DIR/WEB-INF/classes:$LIBPATH  WEB-INF/classes/web/Login.java  -Xlint:deprecation 
javac -cp $TOP_DIR/WEB-INF/classes:$LIBPATH  WEB-INF/classes/web/HelloServlet.java 
javac -cp $TOP_DIR/classes:$LIBPATH  WEB-INF/classes/HelloServlet.java -d ./classes/.  
