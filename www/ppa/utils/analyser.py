from ppa.classifier.classify import Classifier
from ppa.classifier.keywords_extractor import parse_segment, topic


class Analyser(object):
    def __init__(self):
        self._classifier = Classifier()

    def analyse(self, sections):
        l = [0] * len(topic)
        involved_topic = []
        for section in sections:
            if section.subtitle is None:
                continue
            if self._classifier.classify(section.subtitle) == 1:
                parsed_list = parse_segment(section.subtext)
                for i in range(0, len(parsed_list)):
                    if parsed_list[i] or l[i]:
                        l[i] = 1

        for i in range(0, len(topic)):
            if l[i] == 1:
                for e in topic[i]:
                    break
                involved_topic.append(e)
        return involved_topic


instance = Analyser()


def get_instance():
    return instance
