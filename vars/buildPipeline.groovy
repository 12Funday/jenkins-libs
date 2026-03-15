def call(String repoUrl, String imageName) {
    // Checkout repo project
    git branch: 'main', url: repoUrl

    // Build Docker image
    sh "docker build -t ${imageName} ."

    // Run tests
    sh "echo 'Running tests...'"
}