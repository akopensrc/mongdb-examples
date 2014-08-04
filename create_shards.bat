SET SERVER_HOME=C:\servers\mongodb-shards
SET MONGO_HOME=C:\servers\mongodb-shards\mongodb-win32-x86_64-2.4.2

echo Create config servers
start /b %MONGO_HOME%\bin\mongod.exe --configsvr --dbpath "%SERVER_HOME%\configs\config-1\meta-data" --port 26017
start /b %MONGO_HOME%\bin\mongod.exe --configsvr --dbpath "%SERVER_HOME%\configs\config-2\meta-data" --port 26018
start /b %MONGO_HOME%\bin\mongod.exe --configsvr --dbpath "%SERVER_HOME%\configs\config-3\meta-data" --port 26019

echo Start routing server
start /b %MONGO_HOME%\bin\mongos.exe --configdb ishiahm-lt125:26017,ishiahm-lt125:26018,ishiahm-lt125:26019 --port 25017

echo Create replica set - A
start /b %MONGO_HOME%\bin\mongod.exe --replSet india --logpath "%SERVER_HOME%\sets\mongo-1\log\mongod.log" --dbpath "%SERVER_HOME%\sets\mongo-1\data" --port 27011 --oplogSize 64 --smallfiles --rest
start /b %MONGO_HOME%\bin\mongod.exe --replSet india --logpath "%SERVER_HOME%\sets\mongo-2\log\mongod.log" --dbpath "%SERVER_HOME%\sets\mongo-2\data" --port 27012 --oplogSize 64 --smallfiles --rest
start /b %MONGO_HOME%\bin\mongod.exe --replSet india --logpath "%SERVER_HOME%\sets\mongo-3\log\mongod.log" --dbpath "%SERVER_HOME%\sets\mongo-3\data" --port 27013 --oplogSize 64 --smallfiles --rest

echo Create replica set - A
start /b %MONGO_HOME%\bin\mongod.exe --replSet usa --logpath "%SERVER_HOME%\sets\mongo-4\log\mongod.log" --dbpath "%SERVER_HOME%\sets\mongo-4\data" --port 27014 --oplogSize 64 --smallfiles --rest
start /b %MONGO_HOME%\bin\mongod.exe --replSet usa --logpath "%SERVER_HOME%\sets\mongo-5\log\mongod.log" --dbpath "%SERVER_HOME%\sets\mongo-5\data" --port 27015 --oplogSize 64 --smallfiles --rest
start /b %MONGO_HOME%\bin\mongod.exe --replSet usa --logpath "%SERVER_HOME%\sets\mongo-6\log\mongod.log" --dbpath "%SERVER_HOME%\sets\mongo-6\data" --port 27016 --oplogSize 64 --smallfiles --rest