## run mongodb container
docker run --name my_mongodb_cotainer -d -p 27017:27017 mongo:5.0.6

guide on https://codersee.com/a-guide-to-ktor-with-mongodb/

generate doc:
- set cursor on ``embededServer`` in ``Application.kt``
- press `ALT + ENTER`
- select `Generate...`
- restart service
- go to ``/swagger``

todo:
- documentation generator for ktor https://bkbn.gitbook.io/kompendium/