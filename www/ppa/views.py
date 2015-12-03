from django.shortcuts import render


def homepage(request):
    context = {}

    return render(request, 'ppa/index.html', context)

# Create your views here.
