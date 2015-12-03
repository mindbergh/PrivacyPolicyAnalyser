#!/usr/bin/python

import os
import xmltodict

WORKING_DIR = "/Users/fangming/Downloads/corpus/"

policys = []


def make_dict_from_subtitle(word_set, subtitle):
    try:
        for word in subtitle.split():
            word_set.add(word)
    except:
        return


def make_dict():
    word_set = set()
    for root, dirs, files in os.walk(WORKING_DIR):
        for file in files:
            if file.endswith('.xml'):
                with open(os.path.join(root, file), 'r') as f:
                    try:
                        policy = xmltodict.parse(f.read())
                        if policy.has_key('POLICY'):
                            if policy['POLICY'].has_key('SECTION'):
                                for section in policy['POLICY']['SECTION']:
                                    if section.has_key('SUBTITLE'):
                                        subtitle = section['SUBTITLE']
                                        if subtitle is not None:
                                            make_dict_from_subtitle(word_set, subtitle)
                    except:
                        continue

    count = 0
    for word in word_set:
        print word, count
        count += 1


def make_subtexts():
    for root, dirs, files in os.walk(WORKING_DIR):
        for file in files:
            if file.endswith('.xml'):
                with open(os.path.join(root, file), 'r') as f:
                    try:
                        policy = xmltodict.parse(f.read())
                        if policy.has_key('POLICY'):
                            if policy['POLICY'].has_key('SECTION'):
                                for section in policy['POLICY']['SECTION']:
                                    if section.has_key('SUBTITLE'):
                                        subtitle = section['SUBTITLE']
                                        if subtitle is not None:
                                            print subtitle
                    except:
                        continue


if  __name__ == "__main__":
    #make_dict()
    make_subtexts()
