# FEATURES

A simple command-line tool that can be used to download Last.fm scrobble history for any Last.fm user.

There are four different download formats available:
* One large file containing all scrobble history in a slimmed-down form containing only the most relevant data (CSV format).
* Multiple paginated files containing all scrobble history in a slimmed-down form with only the most relevant data fields (CSV format).
* Multiple paginated files containing all scrobble history in a slimmed-down form with only the most relevant data fields (JSON format).
* Multiple paginated files containing all scrobble history as provided verbatim by the Last.fm API (JSON format).

The slimmed-down versions of the scrobble history saves the following attributes in your download and ignores other unnecessary attributes (such as image links):
* Title
* Artist
* Album
* Scrobbled date and time

# SETUP AND USE
* Download the executable.
* Create a designated directory to store the scrobble history and copy the path to it.
* Open the executable (no installation needed).
* Follow the instructions on the screen, providing the path to the designated history directory when prompted.

The download might take a while depending on how much scrobble history needs to be downloaded.

In order to use this tool you will need to have Java installed on your computer.
