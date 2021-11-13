#!/bin/zsh

############################################################
# Description                                              #
############################################################
# This script accept language,
# yaml file path,
# output folder path as arguments and repository if you want to push it into git.
# to generate client code from swagger-codegen library or jar file.


############################################################
# Prerequisites                                            #
############################################################
# You need:
# 1. run this command chmod u+x ./swagger-code-gen-v3.sh
# 2. Homebrew package for install swagger-codegen;
# or
# 3. wget for download swagger-codegen-cli.jar file
# 3.1. java for run this jar file.
# swagger-codegen-cli.jar will be downloaded in the same path that script exists;

######
# This script will check if you have Homebrew package,
# for install swagger-codegen, if homebrew exists will check  swagger-codegen package if not exists
# will install swagger-codegen through Homebrew package.
# if you don't have Homebrew package, will check swagger-codegen-cli.jar , if is exist
# will generate client code, if not exist will check wget package if exist will download swagger-codegen-cli.jar,
# in script file path.


############################################################
# Cases                                                    #
############################################################
# To run this script you should put this script file into main project folder then:
# 1. build project with swagger documentation
# 2. run gradle build to build the project and expose yaml file from build folder.
# 3. pass at least language argument.

#######
# In case you passed wrong language:
# 1. the script validate language value.
# 2. if is not valid will stop and exist the program then Display the supported programming languages.
# 3. if is valid will continue the process;

#######
# In case you did not put this script into project:
# you should pass output folder, input uml file and language.

#######
# In case you did not pass yaml file argument:
# 1. the script will detect yaml file from build folder.

#######
# In case you did not pass output folder argument:
# 1. the script will create default client folder inside main project folder,
# contains languages folder you are generated;

#######
# In case you passed yaml file or output folder as argument:
# 1. the script will generate the client code depending on your passed arguments.


############################################################
# Sample Runs                                              #
############################################################
#./swagger-code-gen-v3.sh  -h
#./swagger-code-gen-v3.sh  -l javascript

#####
#./swagger-code-gen-v3.sh  -y /Users/abassamhussein/Documents/GitHub/alpha-all/alpha-code/alpha-main/alpha-adapter/alpha-customer-adapter/build/classes/java/main/META-INF/swagger/alpha-customer-adapter-api-1.0.yml -o /Users/abassamhussein/Documents/GitHub/alpha-all/alpha-code/alpha-main/alpha-adapter/alpha-customer-adapter/alpha-customer-adapter-client/javascript -l javascript
#./swagger-code-gen-v3.sh  -y /Users/abassamhussein/Documents/GitHub/alpha-all/alpha-code/alpha-main/alpha-adapter/alpha-customer-adapter/build/classes/java/main/META-INF/swagger/alpha-customer-adapter-api-1.0.yml -o /Users/abassamhussein/Desktop/tmp2 -l javascript

#####
#./swagger-code-gen-v4.sh -y openapi.yml  -l t
#./swagger-code-gen-v4.sh -y openapi.yml  -l javascript


############################################################
# Variables                                                #
############################################################
unset LANGUAGE;
unset INPUT_YAML_FILE;
unset OUTPUT_GEN_FOLDER_PATH;
unset REMOTE_REPO;
unset USER_REMOTE_REPO;
unset PASSWORD_REMOTE_REPO;
unset APP_DIR;
unset APP_NAME;
unset SWAGGER_ZIP_FILE_DIR;
SCRIPT="$0";
DEFAULT_INPUT_YAML_FOLDER=build/classes/java/main/META-INF/swagger;
DEFAULT_OUTPUT_GEN_FOLDER_CLIENT_PATH=-client;
LANGUAGES_LIST=(dart
                dynamic-html
                html
                html2
                java
                micronaut
                spring
                nodejs-server
                penapi
                openapi-yaml
                kotlin-client
                kotlin-server
                swift3
                swift4
                swift5
                typescript-angular
                typescript-axios
                ypescript-fetch
                javascript);


############################################################
# Help                                                     #
############################################################
Help() {
   # Display Help
   echo
   echo "This script accept language,
         yaml file path,
         output folder path as arguments and repository if you want to push it into git.
         to generate client code from swagger-codegen library or jar file."
   echo
   echo "Syntax: scriptTemplate [-h|l|y|o|r|ur|tr]"
   echo "options:"
   echo "-h, --help                             Help me !."
   echo "-l, --language                         Programming language you want to generate a client library from"
   echo "-y, --yaml, --yml                      Path of Yaml, Yml file you want to generate client library from."
   echo "-o, -g, --output-gen, --gen-path       Path of output folder, for put result of swagger client."
   echo "-r, --remote-repo                      Repository URL you want to push the result of the swagger client to it."
   echo "-ur, --user-repo                       Repository Username you want to push the result of the swagger client to it."
   echo "-tr, --token-repo                      Repository Tolen you want to push the result of the swagger client to it."
   echo
}

HelpLanguage () {
  echo "✅  you should pass one of following programming languages: ";
  echo "$LANGUAGES_LIST";
}
############################################################
# Process the input options. Add options as needed.        #
############################################################
if [ $# -eq 0 ]; then
  echo "❌  you should pass at least language argument ...";
  Help;
  exit;
fi

ListOfValidLanguage () {
  VALID_LANGUAGE=false;
  for i in "${LANGUAGES_LIST[@]}"
  do
     if [ $i = "$LANGUAGE" ]; then
       VALID_LANGUAGE=true;
       break ;
     fi
  done;

  if [ "$VALID_LANGUAGE" = false ]; then
    echo "❌  you should pass valid language argument ...";
    HelpLanguage;
    exit;
  fi
}

while [ $# -gt 0 ] ; do
  case $1 in
    -h | --help)
      Help
      exit;;
    -l | --language)
      LANGUAGE="$2" ;
      ListOfValidLanguage;;
    -y | --yaml | --yml)
      INPUT_YAML_FILE="$2" ;;
    -o | -g | --output-gen | --gen-path)
      OUTPUT_GEN_FOLDER_PATH="$2" ;;
    -r | --remote-repo)
      REMOTE_REPO="$2" ;;
    -ur | --user-repo)
      USER_REMOTE_REPO="$2" ;;
    -tr | --token-repo)
      PASSWORD_REMOTE_REPO="$2" ;;
    -?* | --?*)
      Help;
      exit;;
  esac;
  shift;
done;


############################################################
# Manage Directory                                         #
############################################################
while [ -h "$SCRIPT" ] ; do
  ls=`ls -ld "$SCRIPT"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    SCRIPT="$link";
  else
    SCRIPT=`dirname "$SCRIPT"`/"$link";
  fi
done;

# Get folder directory, depending on script shell path.
# APP_DIR will represent the main folder of shell path.
if [ ! -d "${APP_DIR}" ]; then
  APP_DIR=`dirname "$SCRIPT"`/;
  APP_DIR=`cd "${APP_DIR}"; pwd`;
  APP_NAME=${APP_DIR##*/};
fi

echo "✅  App Name: $APP_NAME";
echo "✅  App folder path: $APP_DIR";

############################################################
# Check Variables and set default values                   #
############################################################

# set default input yaml file, if you did not pass yml file as argument.
if [ -z $INPUT_YAML_FILE ]; then
  echo "👉 Set default input yaml file ... ";

  # get default openapi yml file path, then assign to INPUT_YAML_FILE.
  DEFAULT_INPUT_YAML_FOLDER=$APP_DIR/$DEFAULT_INPUT_YAML_FOLDER;
  INPUT_YAML_FILE=$(find $DEFAULT_INPUT_YAML_FOLDER -type f -name '*.yml');

  # check if yml file exist in main folder of shell path and exist in 'build/classes/java/main/META-INF/swagger' directory,
  # and the project is it build before or not.
  # if not will exit the process.
  if [ -z $INPUT_YAML_FILE ]; then
    echo "❌  Yml file not found, you should build the project then run script, or pass yml file path in argument ... ";
    echo "❌  Exit Program ..."
    exit ;
  fi
  echo "✅  Input default yaml file path: $INPUT_YAML_FILE";
else
  echo "✅  Input yaml file path: $INPUT_YAML_FILE";
fi;

# set default output folder, if you did not pass output folder as argument.
if [ -z $OUTPUT_GEN_FOLDER_PATH ]; then
  echo "👉 Set default output folder ... ";

  # Create client folder if not exit in main folder of shell path.
  DEFAULT_OUTPUT_GEN_FOLDER_CLIENT_PATH=$APP_DIR/$APP_NAME'-client';
  if [ ! -d $DEFAULT_OUTPUT_GEN_FOLDER_CLIENT_PATH ]; then
    mkdir $DEFAULT_OUTPUT_GEN_FOLDER_CLIENT_PATH;
  fi

  # Create language inside client folder if not exit in main folder of shell path,
  # if exit will remove language folder then create it again.
  OUTPUT_GEN_FOLDER_PATH=$DEFAULT_OUTPUT_GEN_FOLDER_CLIENT_PATH/$LANGUAGE
  if [ -d $OUTPUT_GEN_FOLDER_PATH ]; then
    rm -R $OUTPUT_GEN_FOLDER_PATH;
  fi

  # create language folder in client folder anyway in main folder of shell path.
  mkdir $OUTPUT_GEN_FOLDER_PATH;
  echo "✅  Output default folder path: $OUTPUT_GEN_FOLDER_PATH";
else
  echo "✅  Output folder path: $OUTPUT_GEN_FOLDER_PATH";
fi;

############################################################
# Install Swagger Generator Code                          #
############################################################

# By wget
SWAGGER_ZIP_FILE_DIR=$(find $APP_DIR -type f -name 'swagger-codegen-cli.jar');
InstallSwaggerGeneratorCodeByWget () {

  echo "👉 Try download swagger-codegen-cli jar file in main project folder of script shell path ..."

  # check zip file exist in project directory or not.
  if [ -z $SWAGGER_ZIP_FILE_DIR ]; then
    echo "⭕️  File swagger-codegen-cli.jar not exist in main project folder of script shell path ...";
    SWAGGER_ZIP_FILE_DIR=$APP_DIR/'swagger-codegen-cli.jar';

    # Check if wget package exist or not
    if [ -x "$(command -v wget)" ]; then
      echo "✅  Wget package already exist in the system ...";
      echo "👉 Start to download swagger-codegen-cli.jar in main project folder of script shell path ...";
      echo "👉 wget https://repo1.maven.org/maven2/io/swagger/codegen/v3/swagger-codegen-cli/3.0.21/swagger-codegen-cli-3.0.21.jar -O $SWAGGER_ZIP_FILE_DIR;"
      wget https://repo1.maven.org/maven2/io/swagger/codegen/v3/swagger-codegen-cli/3.0.21/swagger-codegen-cli-3.0.21.jar -O $SWAGGER_ZIP_FILE_DIR;
      echo "✅  Downloaded $SWAGGER_ZIP_FILE_DIR  successfully ...";
    else
      echo "❌  Wget package not found in the system package please install at least wget or homebrew package to configure swagger generate client code ...";
      echo "❌  Exit Program ...";
      exit;
    fi
  else
    echo "✅  File swagger-codegen-cli.jar already exist in main project folder of script shell path ...";
  fi
}

# By brew as default package manager in this shell.
if [ -x "$(command -v brew)" ]; then
  echo "✅  Homebrew package already exists in the system ..."
  if [ ! -x "$(command -v swagger-codegen)" ]; then
      echo "👉 Install swagger-codegen by Homebrew package ...";
      echo "👉️ brew install swagger-codegen ...";
      brew install swagger-codegen;
      echo "✅  Install swagger-codegen successfully ...";
  else
    echo "✅  swagger-codegen package already exist in system ...";
  fi
else
  # Try to install using wget
  echo "⭕️  Homebrew command not found as system package manager ...";
  InstallSwaggerGeneratorCodeByWget;
fi


############################################################
# Generate client code                                     #
############################################################

# By Java
GenerateSwaggerGeneratorCodeByJava () {
  if [ -x "$(command -v java)" ]; then
      echo "👉 Start run swagger generate client code by java jar file ...";
      echo "👉 java -jar $SWAGGER_ZIP_FILE_DIR generate -i $INPUT_YAML_FILE -o $OUTPUT_GEN_FOLDER_PATH -l $LANGUAGE";
      echo;

      # args = -i for input yml file, -o for output folder path, -l for programming language
      #java -jar $SCRIPT/swagger-codegen-cli.jar generate -i $INPUT_YAML_FILE -l $LANGUAGE
      java -jar $SWAGGER_ZIP_FILE_DIR generate -i $INPUT_YAML_FILE -o $OUTPUT_GEN_FOLDER_PATH -l $LANGUAGE;

      echo;
      if [ "$(ls -A $OUTPUT_GEN_FOLDER_PATH)" ]; then
          echo "✅  $LANGUAGE client code, generated successfully by java jar file ...";
      fi
  else
    echo "❌  java command not found in system ...";
    echo "❌  Exit program ...";
    exit;
  fi
}

# By swagger command line by default.
if [ -x "$(command -v swagger-codegen)" ]; then
  echo "👉 Start run swagger-codegen ...";
  echo "👉 swagger-codegen generate -i $INPUT_YAML_FILE -o $OUTPUT_GEN_FOLDER_PATH -l $LANGUAGE";
  echo;

  # args = -i for input yml file, -o for output folder path, -l for programming language
  #swagger-codegen generate -i $INPUT_YAML_FILE -o $OUTPUT_GEN_FOLDER_PATH -l $LANGUAGE
  swagger-codegen generate -i $INPUT_YAML_FILE -o $OUTPUT_GEN_FOLDER_PATH -l $LANGUAGE;

  echo;
  if [ "$(ls -A $OUTPUT_GEN_FOLDER_PATH)" ]; then
      echo "✅  $LANGUAGE client code, generated successfully by swagger-codegen ...";
  fi
else
  echo "⭕️  swagger-codegen package not found in system ...";
  echo "👉 Try with java jar command for generate swagger client code ...";
  GenerateSwaggerGeneratorCodeByJava;
fi


############################################################
# Push to client git repo                                  #
############################################################

EchoRepoInfo () {
  echo;
  echo "✅  Repository information ...";
  echo "👉️ Repository Url : $REMOTE_REPO";
  echo "👉 Repository username : $USER_REMOTE_REPO";
  echo "👉 Repository token/password : $PASSWORD_REMOTE_REPO";
  echo;
}

PushCodeToRepo () {

  if [ -z $DEFAULT_OUTPUT_GEN_FOLDER_CLIENT_PATH ]; then
    DEFAULT_OUTPUT_GEN_FOLDER_CLIENT_PATH=$APP_DIR/$APP_NAME'-client';
  fi

  cd $DEFAULT_OUTPUT_GEN_FOLDER_CLIENT_PATH

  git init
  git add .

  git commit -m "First Commit"
  git remote add origin $REMOTE_REPO

  git remote -v
  git push origin master

  cd
}

if [ ! -z $REMOTE_REPO ]; then
  echo "👉 Try to push generated client code to $REMOTE_REPO";
  if [ -x "$(command -v git)" ]; then
    echo "✅  Push generated client code ...";
    EchoRepoInfo;
    PushCodeToRepo;
  else
    echo "⭕️  Git package not found in system ...";
    if [ -x "$(command -v brew)" ]; then
        echo "👉 Install git command ...";
        echo "👉 brew install git";
        brew install git;
        echo "✅  Install Git successfully ...";
        PushCodeToRepo;
    else
      echo "❌  Homebrew command not found as system package manager to install git package ...";
      echo "❌  Push to remote repository fail ...";
      echo "❌  Exit Program ...";
      exit ;
    fi
  fi
else
  echo "⭕️  This generated client code will not push to any remote repository ...";
fi


############################################################
# Successfully Process !                                   #
############################################################
if [ "$(ls -A $OUTPUT_GEN_FOLDER_PATH)" ]; then
  echo "✅  Generate Client Code, Done 😁 🙌🏻";
else
  echo "❌  Generate Client Code Fail, there is something wrong ... 😭";
fi





























