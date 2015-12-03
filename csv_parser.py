__author__ = 'xiaolongjia'

import csv

result = []

with open('docs.csv', 'rb') as csvfile:
    doc_reader = csv.reader(csvfile, delimiter=',')
    for row in doc_reader:
        numString = row[2]
        numString = numString.split(',')

        contains = False
        ad = False
        third_party = False
        children = False
        for numStringEle in numString:

            if numStringEle != "":
                if int(numStringEle) == 14:
                    ad = True

                if int(numStringEle) in [1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13]:
                    contains = True

                if int(numStringEle) == 8:
                    third_party = True

                if int(numStringEle) == 22:
                    children = True

        if contains:
            row = row + [1]
        else:
            row = row + [0]
            contains = False

        if ad:
            row = row + [1]
        else:
            row = row + [0]
            ad = False

        if third_party:
            row = row + [1]
        else:
            row = row + [0]
            third_party = False

        if children:
            row = row + [1]
        else:
            row = row + [0]
            children = False
        result.append(row)

with open('docs_m.csv', 'w') as csvfile:
    doc_writer = csv.writer(csvfile, delimiter=',', quoting=csv.QUOTE_MINIMAL)
    for csv_row in result:
        doc_writer.writerow(csv_row)
