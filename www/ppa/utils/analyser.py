from ppa.classifier.classify import Classifier
from ppa.classifier.keywords_extractor import parse_segment, topic
import corpus_manager

class Analyser(object):
    def __init__(self):
        self._classifier = Classifier()


    def analyse(self, sections):
        #print "Here!!!!!!"
        l= [0]*len(topic)
        involved_topic=[]
        involved_topic.append("The following topics are involved in this policy:")
        #print topic
        for section in sections:
            if section.subtitle is None:
                continue
            #print "IMHERE-------------"
            print section.subtitle
            if self._classifier.classify(section.subtitle) == 1:
                #print "in the information stage"
                parsed_list= parse_segment(section.subtext)
                #print parsed_list
                for i in range(0,len(parsed_list)):
                    if parsed_list[i] or l[i]:
                        l[i]=1

        for i in range(0,len(topic)):
            if l[i]==1:
                for e in topic[i]:
                    break
                involved_topic.append(e)
        print l
        return involved_topic



                

instance = Analyser()

def get_instance():
    return instance



# if __name__ == '__main__':
#     policy, sections=get_random_policy()
#     analyzer= get_instance()
#     parsed_list=analyzer.analyze(analyzer,sections)
#     for i in range(0,len(parsed_list)):
#         if parsed_list[i]==1:
#             print topic[i]+": mentioned"
#         else:
#             print topic[i]+": not mentioned"
