#!/usr/bin/env bash

# --------------------------------------------------
# 
# Short description what this script does. 
# 
# --------------------------------------------------

set -Eeuo pipefail
trap cleanup SIGINT SIGTERM ERR EXIT

VERSION=1.2.3
script_dir=$(cd "$(dirname "${BASH_SOURCE[0]}")" &>/dev/null && pwd -P)
readonly script_dir

usage() {
  cat <<EOF
USAGE: 
    $(basename "${BASH_SOURCE[0]}") [FLAGS/OPTIONS] <arg1>

FLAGS:
    -f, --flag          Some flag
    -h, --help          Prints help information
    -v, --version       Prints version information
    --no-color          Uses plain text output

OPTIONS:
    -o, --option value  Some option
    -p, --param value   Another option (required)

ARGS: 
    <arg1>              Required argument 
EOF
  exit
}

cleanup() {
  trap - SIGINT SIGTERM ERR EXIT
  # script cleanup here
}

setup_colors() {
  if [[ -t 2 ]] && [[ -z "${NO_COLOR-}" ]] && [[ "${TERM-}" != "dumb" ]]; then
    NOFORMAT='\033[0m' RED='\033[0;31m' GREEN='\033[0;32m' ORANGE='\033[0;33m' BLUE='\033[0;34m' PURPLE='\033[0;35m' CYAN='\033[0;36m' YELLOW='\033[1;33m'
  else
    NOFORMAT='' RED='' GREEN='' ORANGE='' BLUE='' PURPLE='' CYAN='' YELLOW=''
  fi
}

msg() {
  echo >&2 -e "${1-}"
}

die() {
  local msg=$1
  local code=${2-1} # default exit status 1
  msg "$msg"
  exit "$code"
}

version() {
  msg "${BASH_SOURCE[0]} $VERSION"  
  exit 0
}

parse_params() {
  # default values
  MY_FLAG=0
  MY_OPTION="default value"

  while :; do
    case "${1-}" in
    -h | --help) usage ;;
    -v | --version) version ;;
    --no-color) NO_COLOR=1 ;;
    -f | --flag) MY_FLAG=1 ;; 
    -o | --option) 
      MY_OPTION="${2-}"
      shift
      ;;
    -p | --param) 
      MY_PARAM="${2-}"
      shift
      ;;
    -?*) die "Unknown option: $1" ;;
    *) break ;;
    esac
    shift
  done

  MY_ARGS=("$@")

  # check required flags, options and arguments
  [[ -z "${MY_PARAM-}" ]] && die "Missing required option: param"
  [[ ${#MY_ARGS[@]} -eq 0 ]] && die "Missing script argument"

  return 0
}

parse_params "$@"
setup_colors

# script logic here
msg "Flags, options and arguments"
msg
msg "${YELLOW}Flag:      ${NOFORMAT}${MY_FLAG}"
msg "${YELLOW}Option:    ${NOFORMAT}${MY_OPTION}"
msg "${YELLOW}Param:     ${NOFORMAT}${MY_PARAM}"
msg "${YELLOW}Arguments: ${NOFORMAT}${MY_ARGS[*]}"
