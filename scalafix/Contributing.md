#Release
We use sbt-ci-release.
First, kickstart a CI release to Sonatype by pushing a git tag that correspond to the desired commit

```bash
git fetch && git log origin/main --pretty=oneline # choose the commit hash you want to tag
COMMIT_HASH=14a069a3765739f5540129e8220104b17f233020 # change this variable
VERSION=0.9.15 # change this variable
git tag -af "v$VERSION" $COMMIT_HASH -m "v$VERSION" && git push -f origin v$VERSION
```