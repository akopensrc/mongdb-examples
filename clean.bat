SET SERVER_HOME=C:\servers\mongodb-shards

rm -r %SERVER_HOME%\sets\mongo-1\data
rm -r %SERVER_HOME%\sets\mongo-2\data
rm -r %SERVER_HOME%\sets\mongo-3\data
rm -r %SERVER_HOME%\sets\mongo-4\data
rm -r %SERVER_HOME%\sets\mongo-5\data
rm -r %SERVER_HOME%\sets\mongo-6\data

rm -r %SERVER_HOME%\sets\mongo-1\log
rm -r %SERVER_HOME%\sets\mongo-2\log
rm -r %SERVER_HOME%\sets\mongo-3\log
rm -r %SERVER_HOME%\sets\mongo-4\log
rm -r %SERVER_HOME%\sets\mongo-5\log
rm -r %SERVER_HOME%\sets\mongo-6\log

rm -r %SERVER_HOME%\configs\config-1\meta-data
rm -r %SERVER_HOME%\configs\config-2\meta-data
rm -r %SERVER_HOME%\configs\config-3\meta-data

mkdir C:\servers
mkdir %SERVER_HOME%
mkdir %SERVER_HOME%\sets\mongo-1
mkdir %SERVER_HOME%\sets\mongo-2
mkdir %SERVER_HOME%\sets\mongo-3

mkdir %SERVER_HOME%\sets\mongo-1\data
mkdir %SERVER_HOME%\sets\mongo-2\data
mkdir %SERVER_HOME%\sets\mongo-3\data

mkdir %SERVER_HOME%\sets\mongo-1\log
mkdir %SERVER_HOME%\sets\mongo-2\log
mkdir %SERVER_HOME%\sets\mongo-3\log

mkdir %SERVER_HOME%\sets\mongo-4
mkdir %SERVER_HOME%\sets\mongo-5
mkdir %SERVER_HOME%\sets\mongo-6

mkdir %SERVER_HOME%\sets\mongo-4\data
mkdir %SERVER_HOME%\sets\mongo-5\data
mkdir %SERVER_HOME%\sets\mongo-6\data

mkdir %SERVER_HOME%\sets\mongo-4\log
mkdir %SERVER_HOME%\sets\mongo-5\log
mkdir %SERVER_HOME%\sets\mongo-6\log

mkdir %SERVER_HOME%\configs\config-1\meta-data
mkdir %SERVER_HOME%\configs\config-2\meta-data
mkdir %SERVER_HOME%\configs\config-3\meta-data