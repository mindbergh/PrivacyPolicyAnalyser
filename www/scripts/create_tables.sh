#!/usr/bin/env bash

BASE_DIR=".."
DB_PATH="$BASE_DIR/db.sqlite3"
sqlite3 $DB_PATH < ddl.sql
