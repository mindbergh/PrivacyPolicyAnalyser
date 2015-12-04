from django.shortcuts import render

from utils.corpus_manager import policy_selector


def dashboard(request):
    context = {}

    policy, sections = policy_selector()

    context['policy'] = policy
    context['sections'] = sections

    return render(request, 'ppa/index.html', context)
