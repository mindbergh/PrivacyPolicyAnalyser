import xml.etree.ElementTree as ET
import sqlite3
import os

def parse_xml(filename):
	tree = ET.parse(filename)
	root = tree.getroot()
	# conn = sqlite3.connect('../db.sqlite3')
	# c = conn.cursor()
	# c.execute("INSERT INTO policy (title) VALUES (?)",(filename,))
	# line= c.execute("SELECT * FROM policy WHERE title= '%s'" %filename )
	# pid= line.fetchone()[0]
	l_secs=[]
	t_sec=[]
	for section in root.iter("SECTION"):
		subtitle= section.find("SUBTITLE").text
		subtext= section.find("SUBTEXT").text
		#print "subtitle: "+ subtitle
		#print "subtext: "+ subtext
		# c.execute(stmt)
		t_sec.append(subtitle)
		t_sec.append(subtext)
		l_secs.append(t_sec)
		t_sec=[]

	policy=(filename, l_secs)

	return policy


CORPUS_DIR = '../../corpus'


if __name__ == '__main__':
	policys=[]
	files = [f for f in os.listdir(CORPUS_DIR)]
	for f in files:
		if f.endswith("xml"):
			p=parse_xml(os.path.join(CORPUS_DIR, f))
			policys.append(p)
	print len(policys)
