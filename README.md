# HTTP-GET-Requests
This is a program to perform an HTTP Get request on a webpage and return the headers and source code of the page. It only handles one redirect (for example: from http to https).

## Running the Program
To use this program you must first download the program or clone the repository. Inside a terminal, navigate to the path where the program is. Run the command "javac http_client.java" to compile the program. Then run it with "java http_client http://example.com". The result will be placed in a file within the same path called "http_client_output".

This program has been tested in a Linux Ubuntu environment. It has been tested with regular webpages and zip files up to 100MB.

## Other Files
This repository also contains an example of what will print out in the output file and a screenshot of the program being used with wireshark.
