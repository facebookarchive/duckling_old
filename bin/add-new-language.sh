#!/bin/sh

p="$(dirname "$0")/../resources/languages"
l=$p/$1
mkdir "$l"
mkdir "$l/corpus"
mkdir "$l/rules"
