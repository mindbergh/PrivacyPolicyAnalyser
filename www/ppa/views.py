from django.shortcuts import render

from utils.corpus_manager import get_random_policy, get_policy
from utils.analyser import get_instance


def dashboard(request):
    context = {}

    policy, sections = get_random_policy()

    context['policy'] = policy
    context['sections'] = sections

    return render(request, 'ppa/dashboard.html', context)


def analyse(request):
    context = {}

    if request.method == 'POST':
        pid = request.POST['pid']
        policy, sections = get_policy(pid)

        context['policy'] = policy
        context['sections'] = sections

        get_instance().analyse(sections)

    return render(request, 'ppa/dashboard.html', context)
