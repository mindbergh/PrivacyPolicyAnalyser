from ppa.models import Policy, Section
import random


def policy_selector():
    num_policy = Policy.objects.count()
    idx = random.randint(0, num_policy - 1)
    policy = Policy.objects.get(pid=idx);
    sections = Section.objects.filter(pid=idx)
    return policy, sections
