SET SERVER_HOME=C:\servers\mongodb-shards
SET MONGO_HOME=C:\servers\mongodb-shards\mongodb-win32-x86_64-2.4.2

echo Create replica set - A
start /b %MONGO_HOME%\bin\mongod.exe --replSet set1 --logpath "%SERVER_HOME%\sets\mongo-1\log\mongod.log" --dbpath "%SERVER_HOME%\sets\mongo-1\data" --port 27011 --oplogSize 64 --smallfiles --rest
start /b %MONGO_HOME%\bin\mongod.exe --replSet set1 --logpath "%SERVER_HOME%\sets\mongo-2\log\mongod.log" --dbpath "%SERVER_HOME%\sets\mongo-2\data" --port 27012 --oplogSize 64 --smallfiles --rest
start /b %MONGO_HOME%\bin\mongod.exe --replSet set1 --logpath "%SERVER_HOME%\sets\mongo-3\log\mongod.log" --dbpath "%SERVER_HOME%\sets\mongo-3\data" --port 27013 --oplogSize 64 --smallfiles --rest