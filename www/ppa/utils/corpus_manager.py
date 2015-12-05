from ppa.models import Policy, Section
import random


def get_random_policy():
    num_policy = Policy.objects.count()
    idx = random.randint(0, num_policy - 1)
    policy = Policy.objects.get(pid=idx)
    sections = Section.objects.filter(pid=idx)
    return policy, sections


def get_policy(pid):
    policy = Policy.objects.get(pid=pid)
    sections = Section.objects.filter(pid=pid)
    return policy, sections
