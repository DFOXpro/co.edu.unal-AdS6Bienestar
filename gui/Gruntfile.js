/* global module */

module.exports = function (grunt) {
	// load all grunt tasks matching the ['grunt-*', '@*/grunt-*'] patterns
	require('load-grunt-tasks')(grunt);

	var paginaARenderizar = {
		//LANDIGN,
		"build/index.html": 'src/index.jade',
		"build/registro_general.html": 'src/registro_general.jade',

		"build/admin.html": 'src/administrador.jade',
		"build/admin1.html": 'src/theme/Vistas_JADE/admin_main.jade',
		"build/usuario.html": 'src/usuario.jade',

		//ERRORES
		"build/401.html": 'src/paginas_de_error/401.jade',
		"build/404.html": 'src/paginas_de_error/404.jade',
		"build/500.html": 'src/paginas_de_error/500.jade',
		"build/503.html": 'src/paginas_de_error/503.jade'//,
	};


	// Project configuration.
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),

		copy: {
			devLib: {
				expand: true,
				filter: 'isFile',
				flatten: true,
				cwd: './bower_components/',
				src: [
					'angular/angular.min.js',
					'livereload-js/dist/livereload.js',
					'cryptico/cryptico.min.js'
				],
				dest: 'build/js/lib/'
			},
			devSrc: {
				expand: true,
				cwd: 'src/js/',
				src: '**',
				dest: 'build/js/'
			},
			devThemes: {
				expand: true,
				cwd: 'src/css/',
				src: '**',
				dest: 'build/css/'
			},
			toServer: {
				expand: true,
				cwd: 'build/',
				src: '**',
				dest: '../server/web/'
			}
		},

		jade: {
			dev: {
				options: {
					pretty: true,
					data: {
						debug: true
					}
				},
				files: paginaARenderizar
			},
			prod: {
				options: {
					pretty: false,
					data: {
						debug: false
					}
				},
				files: paginaARenderizar
			}
		},

		stylus: {
			dev: {
				options: {
					compress: false,
					'include css': true
				},
				files: {
					'build/main.css': 'src/css/main.styl'
					//'build/decoy.css': 'src/css/decoy.styl',
				//'path/to/another.css': ['path/to/sources/*.styl', 'path/to/more/*.styl'] // compile and concat into single file
				}
			}
		},

		watch: {
			views: {
				files: ['src/**/*.jade', 'src/**/*.svg'],
				tasks: ['jade:dev']
			},
			css: {
				files: [
					'src/css/*.styl',
					'src/css/*.css'
				],
				tasks: [
					//'stylus:dev'
					'copy:devThemes'
				]
			},
			js: {
				files: 'src/js/**/*.js',
				tasks: ['copy:devSrc']
			},
			Server: {
				files: 'build/**/*',
				tasks: ['copy:toServer']
			},
			Client: {
				files: 'build/**/*',
				options: {
					livereload: true
				}
			}
		}
	});


	// Default task(s).
	grunt.registerTask(
		'client', [
			'jade:dev',
			'copy:devLib',
			'copy:devSrc',
			'copy:devThemes',
			'watch:views',
			'watch:css',
			'watch:js',
			'watch:Client'
		]
	);
	grunt.registerTask(
		'toServer', [
			'jade:dev',
			'copy',
			'watch'
		]
	);
	grunt.registerTask('default', 'toServer');
};
