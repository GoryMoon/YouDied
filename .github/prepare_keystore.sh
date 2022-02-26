#!/bin/bash

echo "$RELEASE_KEYSTORE" > release.keystore.asc
gpg -d --quiet --passphrase "$RELEASE_KEYSTORE_PASSPHRASE" --batch -o release.keystore release.keystore.asc