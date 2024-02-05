# Specify the parent directory where your Maven projects are located
$parentDirectory = Get-Location

# Get a list of subdirectories in the parent directory
$projectDirectories = Get-ChildItem -Path $parentDirectory -Directory
$excludedDirectory = "todo-project-TypeScriptGenerator"

foreach ($projectDirectory in $projectDirectories) {
    # Check if the directory is the excluded directory
    if ($projectDirectory.Name -eq $excludedDirectory) {
        Write-Host "Skipping excluded directory: $($projectDirectory.FullName)"
        continue
    }

    # Check if the directory contains a pom.xml file
    $pomFile = Join-Path $projectDirectory.FullName "pom.xml"
    if (Test-Path $pomFile) {
        Write-Host "Building Maven project in: $($projectDirectory.FullName)"

        # Change to the project directory and perform Maven build
        Set-Location -Path $projectDirectory.FullName
        mvn clean verify

        # Return to the original directory
        Set-Location -Path $parentDirectory
    } else {
        Write-Host "No Maven project found in: $($projectDirectory.FullName)"
    }
}