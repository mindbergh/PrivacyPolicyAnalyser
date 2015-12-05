from ppa.classifier.classify import Classifier


class Analyser(object):
    def __init__(self):
        self._classifier = Classifier()

    def analyse(self, sections):
        topic = set()
        for section in sections:
            if section.subtitle is None:
                continue
            if self._classifier.classify(section.subtitle) == 1:
                print section.subtitle


instance = Analyser()

def get_instance():
    return instance



