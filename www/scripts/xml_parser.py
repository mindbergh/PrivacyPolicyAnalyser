import xml.etree.ElementTree as ET
import sqlite3

def parse_xml(filename):
	tree = ET.parse(filename)
	root = tree.getroot()
	policy={}
	conn = sqlite3.connect('../db.sqlite3')
	c = conn.cursor()
	c.execute("INSERT INTO policy (title) VALUES (?)",(filename,))
	line= c.execute("SELECT * FROM policy WHERE title= '%s'" %filename )
	pid= line.fetchone()[0]
	for section in root.iter("SECTION"):
		subtitle= section.find("SUBTITLE").text
		subtext= section.find("SUBTEXT").text
		policy[subtitle]= subtext
		#print "subtitle: "+ subtitle
		#print "subtext: "+ subtext
		stmt = "INSERT INTO section (pid,subtitle,subtext) VALUES (%d,'%s','%s')" % (pid, subtitle,subtext)
		print stmt
		c.execute(stmt)
	#print policy



if __name__ == '__main__':
	filename='9gag.xml'
	parse_xml(filename)
