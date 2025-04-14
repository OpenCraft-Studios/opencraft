# include <stdio.h>
# include <GLFW/glfw3.h>
# include <glad/glad.h>

static GLFWwindow *window;

int main(void)
{
    window = glfwCreateWindow(640, 480, "OpenCraft 25r01", NULL, NULL);
	if (!glfwInit())
	{
		fprintf(stderr, "[ main/ERROR ]: Unable to start GLFW!\n");
		return -1;
	}

	window = glfwCreateWindow(640, 480, "OpenCraft 25r01", NULL, NULL);
	if (!window)
	{
		glfwTerminate();
		return -1;
	}

	glfwMakeContextCurrent(window);

	if (!gladLoadGLLoader((GLADloadproc)glfwGetProcAddress))
	{
		fprintf(stderr, "[ main/ERROR ]: Failed to initialize GLAD!\n");
		return -1;
	}

	glClearColor(1, 0, 0, 1);

	while (!glfwWindowShouldClose(window))
	{
		glClear(GL_COLOR_BUFFER_BIT);
		glfwSwapBuffers(window);
		glfwPollEvents();
	}

	glfwDestroyWindow(window);
	glfwTerminate();
	return 0;
}
