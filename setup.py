from os import path
from setuptools import setup

with open(path.join(path.dirname(path.abspath(__file__)), 'README.rst')) as f:
    readme = f.read()

setup(
    name             = 'graph2table',
    version          = '0.1',
    description      = 'An app to ...',
    long_description = readme,
    author           = 'Sandip Samal',
    author_email     = 'sandip.samal@childrens.harvard.edu',
    url              = 'http://wiki',
    packages         = ['graph2table'],
    install_requires = ['chrisapp'],
    test_suite       = 'nose.collector',
    tests_require    = ['nose'],
    license          = 'MIT',
    zip_safe         = False,
    python_requires  = '>=3.6',
    entry_points     = {
        'console_scripts': [
            'graph2table = graph2table.__main__:main'
            ]
        }
)
