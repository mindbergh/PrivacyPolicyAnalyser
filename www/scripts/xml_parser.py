import os
import xml.etree.ElementTree as ET
import sqlite3
import json


def parse_xml(filename):
    tree = ET.parse(filename)
    root = tree.getroot()
    # conn = sqlite3.connect('../db.sqlite3')
    # c = conn.cursor()
    # c.execute("INSERT INTO policy (title) VALUES (?)",(filename,))
    # line= c.execute("SELECT * FROM policy WHERE title= '%s'" %filename )
    # pid= line.fetchone()[0]
    l_secs = []
    t_sec = []
    for section in root.iter("SECTION"):
        subtitle = section.find("SUBTITLE").text
        subtext = section.find("SUBTEXT").text
        # print "subtitle: "+ subtitle
        # print "subtext: "+ subtext
        # c.execute(stmt)
        t_sec.append(subtitle)
        t_sec.append(subtext)
        l_secs.append(t_sec)
        t_sec = []

    policy = (filename, l_secs)

    return policy


BASE_PATH = '..'
DB_PATH = os.path.join(BASE_PATH, 'db.sqlite3')
CORPUS_DIR = '../../corpus'
INSERT_POLICY = "INSERT INTO policy (title) VALUES (?)"
INSERT_SECTIONS = "INSERT INTO section (pid, substtle, subtext) VALUES (?, ?, ?)"


def parse_xml(filename):
    tree = ET.parse(filename)
    root = tree.getroot()
    policy = {}
    conn = sqlite3.connect('../db.sqlite3')
    c = conn.cursor()
    c.execute("INSERT INTO policy (title) VALUES (?)", (filename,))
    line = c.execute("SELECT * FROM policy WHERE title= '%s'" % filename)
    pid = line.fetchone()[0]
    for section in root.iter("SECTION"):
        subtitle = section.find("SUBTITLE").text
        subtext = section.find("SUBTEXT").text
        policy[subtitle] = subtext
        # print "subtitle: "+ subtitle
        # print "subtext: "+ subtext
        stmt = "INSERT INTO section (pid,subtitle,subtext) VALUES (%d,'%s','%s')" % (pid, subtitle, subtext)
        print stmt
        c.execute(stmt)
        # print policy


def policy_mapper(policy):
    return json.dumps(policy[0])


def section_mapper(policy):
    p = dbc.execute("SELECT * FROM policy WHERE title = '%s'" % json.dumps(policy[0]))
    pid = p.fetchone()[0]
    return [(pid, json.dumps(subtitle), json.dumps(subtext)) for subtitle, subtext in policy[1]]


def policy_loader(policies, dbc):
    dbc.executemany(INSERT_POLICY,
                    map(policy_mapper, policies))
    dbc.executemany(INSERT_SECTIONS,
                    map(section_mapper, policies))


if __name__ == '__main__':
    policys = []
    files = [f for f in os.listdir(CORPUS_DIR)]
    for f in files:
        if f.endswith("xml"):
            p = parse_xml(os.path.join(CORPUS_DIR, f))
            policys.append(p)
    print len(policys)

    conn = sqlite3.connect(DB_PATH)
    dbc = conn.cursor()

    policy_loader(policys, dbc)
    dbc.close()
