# FEATURES

A simple tool that can be used to download Last.fm scrobble history for any Last.fm user.

Three different "modes" of download are available:
* Verbatim download of scrobble history as provided by the Last.fm API (JSON format).
* Slimmed down scrobble history containing only the most relevant data in JSON format.
* Slimmed down scrobble history containing only the most relevant data in CSV format (this will take up the least amount of space on storage).

# SETUP
In order to use this tool you will need to have Java and Maven installed and configured.
* Download the project (remember to extract it if you downloaded a compressed version).
* Open up a terminal window in the project root directory (you should see `pom.xml` in here).
* Run the following command in the terminal: `mvn prepare-package`
* After the command has executed, open up the `target` folder and open a new terminal window. You should see a file called `lastfm-1.0-jar-with-dependencies.jar`.
* Run the following command in the terminal: `java -jar lastfm-1.0-jar-with-dependencies.jar`
* Follow the instructions on the screen.

The download might take a while depending on how much scrobble history needs to be downloaded.
