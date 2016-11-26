# Util libraries

## Compression

Swampmachine provides several Codec implementations;

### Usage

Create new Codec instance;
Invoke "compress" and "decompress" methods accordingly.

### Tech

Compression uses snappy-java, xz and commons-compress to work.


## Archiving

Swampmachine provides Tar Archiver implementation;

### Usage

Create new Archiver instance;
Invoke "archive" and "unarchive" methods accordingly.

### Tech


Archiving uses commons-compress to work.