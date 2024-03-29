#!/bin/bash

echo "$RELEASE_KEYSTORE" > release.keystore.asc
gpg -d --quiet --passphrase "$RELEASE_KEYSTORE_PASSPHRASE" --batch -o release.keystore release.keystore.asc
cp release.keystore forge/
cp release.keystore fabric/
cp release.keystore quilt/