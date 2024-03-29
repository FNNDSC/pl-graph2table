#
# graph2table ds ChRIS plugin app
#
# (c) 2021 Fetal-Neonatal Neuroimaging & Developmental Science Center
#                   Boston Children's Hospital
#
#              http://childrenshospital.org/FNNDSC/
#                        dev@babyMRI.org
#

from chrisapp.base import ChrisApp
import os
import platform
import os.path,subprocess
from subprocess import STDOUT,PIPE
import numpy as np
Gstr_title = r"""
                       _      _____  _        _     _      
                      | |    / __  \| |      | |   | |     
  __ _ _ __ __ _ _ __ | |__  `' / /'| |_ __ _| |__ | | ___ 
 / _` | '__/ _` | '_ \| '_ \   / /  | __/ _` | '_ \| |/ _ \
| (_| | | | (_| | |_) | | | |./ /___| || (_| | |_) | |  __/
 \__, |_|  \__,_| .__/|_| |_|\_____/ \__\__,_|_.__/|_|\___|
  __/ |         | |                                        
 |___/          |_|                                        
"""

Gstr_synopsis = """

(Edit this in-line help for app specifics. At a minimum, the 
flags below are supported -- in the case of DS apps, both
positional arguments <inputDir> and <outputDir>; for FS and TS apps
only <outputDir> -- and similarly for <in> <out> directories
where necessary.)

    NAME

       graph2table

    SYNOPSIS

        docker run --rm fnndsc/pl-graph2table graph2table                     \\
            [-h] [--help]                                               \\
            [--json]                                                    \\
            [--man]                                                     \\
            [--meta]                                                    \\
            [--savejson <DIR>]                                          \\
            [-v <level>] [--verbosity <level>]                          \\
            [--version]                                                 \\
            <inputDir>                                                  \\
            <outputDir> 

    BRIEF EXAMPLE

        * Bare bones execution

            docker run --rm -u $(id -u)                             \
                -v $(pwd)/in:/incoming -v $(pwd)/out:/outgoing      \
                fnndsc/pl-graph2table graph2table                        \
                /incoming /outgoing

    DESCRIPTION

        `graph2table` ...

    ARGS

        [-h] [--help]
        If specified, show help message and exit.
        
        [--json]
        If specified, show json representation of app and exit.
        
        [--man]
        If specified, print (this) man page and exit.

        [--meta]
        If specified, print plugin meta data and exit.
        
        [--savejson <DIR>] 
        If specified, save json representation file to DIR and exit. 
        
        [-v <level>] [--verbosity <level>]
        Verbosity level for app. Not used currently.
        
        [--version]
        If specified, print version number and exit. 
"""


class Graph2table(ChrisApp):
    """
    An app to ...
    """
    PACKAGE                 = __package__
    TITLE                   = 'A ChRIS plugin app'
    CATEGORY                = ''
    TYPE                    = 'ds'
    ICON                    = ''   # url of an icon image
    MIN_NUMBER_OF_WORKERS   = 1    # Override with the minimum number of workers as int
    MAX_NUMBER_OF_WORKERS   = 1    # Override with the maximum number of workers as int
    MIN_CPU_LIMIT           = 1000 # Override with millicore value as int (1000 millicores == 1 CPU core)
    MIN_MEMORY_LIMIT        = 200  # Override with memory MegaByte (MB) limit as int
    MIN_GPU_LIMIT           = 0    # Override with the minimum number of GPUs as int
    MAX_GPU_LIMIT           = 0    # Override with the maximum number of GPUs as int

    # Use this dictionary structure to provide key-value output descriptive information
    # that may be useful for the next downstream plugin. For example:
    #
    # {
    #   "finalOutputFile":  "final/file.out",
    #   "viewer":           "genericTextViewer",
    # }
    #
    # The above dictionary is saved when plugin is called with a ``--saveoutputmeta``
    # flag. Note also that all file paths are relative to the system specified
    # output directory.
    OUTPUT_META_DICT = {}

    def define_parameters(self):
        """
        Define the CLI arguments accepted by this plugin app.
        Use self.add_argument to specify a new app argument.
        """
        self.add_argument(  '--comment',
                            dest        = 'comment',
                            type        = str,
                            optional    = True,
                            help        = "a simple string to print",
                            default     = "")

    def run(self, options):
        """
        Define the code to be run by this plugin app.
        """
        print(Gstr_title)
        print('Version: %s' % self.get_version())
       
        #path =options.inputdir+'/file.txt'
        #a_WORKFLOWSPEC=np.genfromtxt(path, delimiter=',',usecols=np.arange(0,1))
        
        a_WORKFLOWSPEC=('0:0 fnndsc/pl-dircopy',                            
        "0:1 fnndsc/pl-simpledsapp",
        "1:2 fnndsc/pl-pfdicom_tagsub",
        "1:3 fnndsc/pl-pfdicom_tagextract",               
        "2:4 fnndsc/pl-pfdicom_tagextract",                  
        "2:5 fnndsc/pl-fshack",      
        "5:6 fnndsc/pl-multipass",
        "6:7 fnndsc/pl-pfdorun",
        "5:8 fnndsc/pl-mgz2lut_report")
        

        
        x=[]
        y=[]
        plugins=[]

        for item in a_WORKFLOWSPEC:
            coordinates = item.split(" ")[0].split(':')
            plugins.append(item.split(" ")[-1])
            # x axis values
            x.append( coordinates[0])
            # corresponding y axis values
            y.append( coordinates[1])


        # Compile java files present in JAVA dir
        java_files=[d for d in os.listdir("java") if os.path.isfile(os.path.join("java",d))]
        for java_file in java_files:
            self.compile_java(java_file)
        
        stdin = [str(x),str(y),str(plugins)]
        self.execute_java('GraphImpl',stdin )

    def show_man_page(self):
        """
        Print the app's man page.
        """
        print(Gstr_synopsis)
    def compile_java(self,java_file):
        subprocess.check_call(['javac', java_file])
        

    def execute_java(self,java_file, stdin):
        java_class,ext = os.path.splitext(java_file)
        cmd = ['java', java_class]
        for arg in stdin:
            cmd.append(arg)
       
        proc = subprocess.run(cmd, stdout=PIPE, stderr=STDOUT)
        print (proc.stdout.decode("utf-8"))
