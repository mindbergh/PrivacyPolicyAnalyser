from ppa.classifier.classify import Classifier


class Analyser(object):
    def __init__(self):
        self._classifier = Classifier()


    def analyse(self, sections):
        topic = set()
        for section in sections:
            if self._classifier._classify(section.subtitle) == 1:
                pass


instance = Analyser()

def get_instance():
    return instance



