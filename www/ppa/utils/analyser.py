from ppa.classifier.classify import Classifier
import ppa.classifier.keywords_extractor
import corpus_manager

class Analyser(object):
    def __init__(self):
        self._classifier = Classifier()

    def analyse(self, sections):
        topic = set()
        l= [0]*len(topic)
        for section in sections:
            if section.subtitle is None:
                continue
            if self._classifier._classify(section.subtitle) == 1:
                parsed_list= parse_segment(section)
                l= merge_list(l, parsed_list)
        return l

    def merge_list(l1,l2):
        pass
        l=[0]*len(l1)
        for i in range(0,len(l1)):
            if l1[i] or l2[i]:
                l[i]=1
        return l


                

instance = Analyser()

def get_instance():
    return instance



if __name__ == '__main__':
    policy, sections=get_random_policy()
    analyzer= get_instance()
    parsed_list=analyzer.analyze(analyzer,sections)
    for i in range(0,len(parsed_list)):
        if parsed_list[i]==1:
            print topic[i]+": mentioned"
        else:
            print topic[i]+": not mentioned"
