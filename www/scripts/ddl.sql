DROP TABLE IF EXISTS policy;
DROP TABLE IF EXISTS section;

CREATE TABLE policy (
  pid   INTEGER PRIMARY KEY AUTOINCREMENT,
  title TEXT NOT NULL
);

CREATE TABLE section (
  sid      INTEGER PRIMARY KEY,
  pid      INTEGER NOT NULL,
  subtitle TEXT,
  subtext  TEXT,
  FOREIGN KEY (pid) REFERENCES policy (pid)
);